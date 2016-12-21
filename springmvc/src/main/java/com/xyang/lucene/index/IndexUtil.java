package com.xyang.lucene.index;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @描述
 * @date 2016年11月9日-下午10:13:22
 * @author IBM
 *
 */
public class IndexUtil {
	protected String[] ids = { "1", "2", "3", "4", "5", "6" };
	protected String[] emails = { "aa@itat.org", "bb@itat.org", "cc@cc.org", "dd@sina.org", "ee@zttc.edu",
			"ff@itat.org" };
	protected String[] contents = { "welcome to visited the space,I like book", "hello boy, I like pingpeng ball",
			"my name is cc I like game", "I like football", "I like football and I like basketball too",
			"I like movie and swim" };
	protected Date[] dates = null;
	protected int[] attachs = { 2, 3, 1, 4, 5, 5 };
	protected String[] names = { "zhangsan", "lisi", "john", "jetty", "mike", "jake" };
	protected Directory directory = null;
	protected Map<String, Float> scores = new HashMap<String, Float>();
	protected static IndexReader reader = null;

	protected static final String index_save_dir = "E:/data/lucene/index";
	protected static final String parseFile_save_dir = "E:/data/lucene/example";

	public IndexUtil() {
		try {
			setDates();
			scores.put("itat.org", 2.0f);
			scores.put("zttc.edu", 1.5f);
			directory = FSDirectory.open(new File(index_save_dir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createIndex() {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
			writer.deleteAll();
			Document doc = null;
			// 模拟邮件信息
			for (int i = 0; i < ids.length; i++) {
				doc = new Document();
				doc.add(new Field("id", ids[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
				doc.add(new Field("email", emails[i], Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("email", "test" + i + "@test.com", Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("content", contents[i], Field.Store.NO, Field.Index.ANALYZED));
				doc.add(new Field("name", names[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
				// 存储数字
				doc.add(new NumericField("attach", Field.Store.YES, true).setIntValue(attachs[i]));
				// 存储日期
				doc.add(new NumericField("date", Field.Store.YES, true).setLongValue(dates[i].getTime()));

				String et = emails[i].substring(emails[i].lastIndexOf("@") + 1);
				System.out.println(et);
				if (scores.containsKey(et)) {// 加权处理
					doc.setBoost(scores.get(et));
				} else {
					doc.setBoost(0.5f);
				}
				writer.addDocument(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public IndexSearcher getSearcher() {
		try {
			if (reader == null) {
				// 默认按只读方式打开，用reader操作delete须更改
				reader = IndexReader.open(directory, false);
			} else {
				// null if there are no changes; else, a new IndexReader
				// instance which you must eventually close
				IndexReader tr = IndexReader.openIfChanged(reader);
				if (tr != null) {
					reader.close();
					reader = tr;
				}
			}
			return new IndexSearcher(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private void setDates() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dates = new Date[ids.length];
			dates[0] = sdf.parse("2010-02-19");
			dates[1] = sdf.parse("2012-01-11");
			dates[2] = sdf.parse("2011-09-19");
			dates[3] = sdf.parse("2010-12-22");
			dates[4] = sdf.parse("2012-01-01");
			dates[5] = sdf.parse("2011-05-19");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @描述 索引恢复
	 * @date 2016年11月6日-下午10:41:01
	 */
	public void undeleteAll() {
		// 使用IndexReader进行恢复
		try {
			IndexReader reader = IndexReader.open(directory, false);
			// 恢复时，必须把IndexReader的只读(readOnly)设置为false
			reader.undeleteAll();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void forceDelete() {
		IndexWriter writer = null;

		try {
			writer = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
			writer.forceMergeDeletes();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void merge() {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
			// 会将索引合并为2段，这两段中的被删除的数据会被清空
			// 特别注意：此处Lucene在3.5之后不建议使用，因为会消耗大量的开销，
			// Lucene会根据情况自动处理的
			writer.forceMerge(2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteByWriter(String field, String text) {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
			// 参数是一个选项，可以是一个Query-一些列的，也可以是一个term，term是一个精确查找的值
			// 此时删除的文档并不会被完全删除，而是存储在一个回收站中的，可以恢复
			writer.deleteDocuments(new Term(field, text));
			writer.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteByReader(String field, String value) {
		try {
			reader.deleteDocuments(new Term(field, value));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @描述 索引更新
	 * @date 2016年11月6日-下午10:46:05
	 */
	public void update() {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
			/*
			 * Lucene并没有提供更新，这里的更新操作其实是如下两个操作的合集 先删除之后再添加
			 */
			Document doc = new Document();
			doc.add(new Field("id", "11", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
			doc.add(new Field("email", emails[0], Field.Store.YES, Field.Index.NOT_ANALYZED));
			doc.add(new Field("content", contents[0], Field.Store.NO, Field.Index.ANALYZED));
			doc.add(new Field("name", names[0], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
			writer.updateDocument(new Term("id", "1"), doc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void query() {
		try {
			IndexReader reader = IndexReader.open(directory);
			// 通过reader可以有效的获取到文档的数量
			System.out.println("numDocs:" + reader.numDocs());
			System.out.println("maxDocs:" + reader.maxDoc());
			System.out.println("deleteDocs:" + reader.numDeletedDocs());
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void search01() {
		try {
			IndexReader reader = IndexReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);
			TermQuery query = new TermQuery(new Term("email", "test0@test.com"));
			TopDocs tds = searcher.search(query, 10);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println("(" + sd.doc + "-" + doc.getBoost() + "-" + sd.score + ")" + doc.get("name") + "["
						+ doc.get("email") + "]-->" + doc.get("id") + "," + doc.get("attach") + "," + doc.get("date")
						+ "," + doc.getValues("email")[1]);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void search02() {
		try {
			IndexSearcher searcher = getSearcher();
			TermQuery query = new TermQuery(new Term("content", "like"));
			TopDocs tds = searcher.search(query, 10);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(
						doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->" + doc.get("id")
								+ "," + doc.get("attach") + "," + doc.get("date") + "," + doc.getValues("email")[1]);
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
