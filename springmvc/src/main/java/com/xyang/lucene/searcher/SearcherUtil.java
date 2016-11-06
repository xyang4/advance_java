package com.xyang.lucene.searcher;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.xyang.lucene.index.FileIndexUtils;
import com.xyang.lucene.index.IndexUtil;

public class SearcherUtil extends IndexUtil {
	public SearcherUtil() {
		// directory = new RAMDirectory();
		try {
			createIndex();
			directory = FSDirectory.open(new File(index_save_dir + "_1"));
			scores.put("itat.org", 2.0f);
			scores.put("zttc.edu", 1.5f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IndexSearcher getSearcher() {
		try {
			if (reader == null) {
				reader = IndexReader.open(directory);
			} else {
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

	public IndexSearcher getSearcher(Directory directory) {
		try {
			if (reader == null) {
				reader = IndexReader.open(directory);
			} else {
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

	public void searchByTerm(String field, String name, int num) {
		try {
			IndexSearcher searcher = getSearcher();
			Query query = new TermQuery(new Term(field, name));
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->"+ doc.get("id") + "," + doc.get("attach") + "," + doc.get("date"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByTermRange(String field, String start, String end, int num) {
		try {
			IndexSearcher searcher = getSearcher();
			Query query = new TermRangeQuery(field, start, end, true, true);
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->"
						+ doc.get("id") + "," + doc.get("attach") + "," + doc.get("date"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByNumricRange(String field, int start, int end, int num) {
		try {
			IndexSearcher searcher = getSearcher();
			Query query = NumericRangeQuery.newIntRange(field, start, end, true, true);
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->"
						+ doc.get("id") + "," + doc.get("attach") + "," + doc.get("date"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByPrefix(String field, String value, int num) {
		try {
			IndexSearcher searcher = getSearcher();
			Query query = new PrefixQuery(new Term(field, value));
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->"
						+ doc.get("id") + "," + doc.get("attach") + "," + doc.get("date"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByWildcard(String field, String value, int num) {
		try {
			IndexSearcher searcher = getSearcher();
			// 在传入的value中可以使用通配符:?和*,?表示匹配一个字符，*表示匹配任意多个字符
			Query query = new WildcardQuery(new Term(field, value));
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->"
						+ doc.get("id") + "," + doc.get("attach") + "," + doc.get("date"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByBoolean(int num) {
		try {
			IndexSearcher searcher = getSearcher();
			BooleanQuery query = new BooleanQuery();
			/*
			 * BooleanQuery可以连接多个子查询 Occur.MUST表示必须出现 Occur.SHOULD表示可以出现
			 * Occur.MUSE_NOT表示不能出现
			 */
			query.add(new TermQuery(new Term("name", "zhangsan")), Occur.MUST_NOT);
			query.add(new TermQuery(new Term("content", "game")), Occur.SHOULD);
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->"
						+ doc.get("id") + "," + doc.get("attach") + "," + doc.get("date"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByPhrase(int num) {
		try {
			IndexSearcher searcher = getSearcher();
			PhraseQuery query = new PhraseQuery();
			query.setSlop(3);
			query.add(new Term("content", "pingpeng"));
			// 第一个Term
			query.add(new Term("content", "i"));
			// 产生距离之后的第二个Term
			// query.add(new Term("content","football"));
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->"
						+ doc.get("id") + "," + doc.get("attach") + "," + doc.get("date"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByFuzzy(int num) {
		try {
			IndexSearcher searcher = getSearcher();
			FuzzyQuery query = new FuzzyQuery(new Term("name", "mase"), 0.4f, 0);
			System.out.println(query.getPrefixLength());
			System.out.println(query.getMinSimilarity());
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->"
						+ doc.get("id") + "," + doc.get("attach") + "," + doc.get("date"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByQueryParse(Query query, int num) {
		try {
			IndexSearcher searcher = getSearcher();
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:" + tds.totalHits);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(doc.get("id") + "---->" + doc.get("name") + "[" + doc.get("email") + "]-->"
						+ doc.get("id") + "," + doc.get("attach") + "," + doc.get("date") + "==" + sd.score);
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchPage(String query, int pageIndex, int pageSize) {
		try {
			Directory dir = FileIndexUtils.getDirectory();
			IndexSearcher searcher = getSearcher(dir);
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
			Query q = parser.parse(query);
			TopDocs tds = searcher.search(q, 500);
			ScoreDoc[] sds = tds.scoreDocs;
			int start = (pageIndex - 1) * pageSize;
			int end = pageIndex * pageSize;
			for (int i = start; i < end; i++) {
				Document doc = searcher.doc(sds[i].doc);
				System.out.println(sds[i].doc + ":" + doc.get("path") + "-->" + doc.get("filename"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据页码和分页大小获取上一次的最后一个ScoreDoc
	 */
	private ScoreDoc getLastScoreDoc(int pageIndex, int pageSize, Query query, IndexSearcher searcher)
			throws IOException {
		if (pageIndex == 1)
			return null;// 如果是第一页就返回空
		int num = pageSize * (pageIndex - 1);// 获取上一页的数量
		TopDocs tds = searcher.search(query, num);
		return tds.scoreDocs[num - 1];
	}

	public void searchPageByAfter(String query, int pageIndex, int pageSize) {
		try {
			Directory dir = FileIndexUtils.getDirectory();
			IndexSearcher searcher = getSearcher(dir);
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
			Query q = parser.parse(query);
			// 先获取上一页的最后一个元素
			ScoreDoc lastSd = getLastScoreDoc(pageIndex, pageSize, q, searcher);
			// 通过最后一个元素搜索下页的pageSize个元素
			TopDocs tds = searcher.searchAfter(lastSd, q, pageSize);
			for (ScoreDoc sd : tds.scoreDocs) {
				Document doc = searcher.doc(sd.doc);
				System.out.println(sd.doc + ":" + doc.get("path") + "-->" + doc.get("filename"));
			}
			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchNoPage(String query) {
		try {
			Directory dir = FileIndexUtils.getDirectory();
			IndexSearcher searcher = getSearcher(dir);
			QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
			Query q = parser.parse(query);
			TopDocs tds = searcher.search(q, 20);
			ScoreDoc[] sds = tds.scoreDocs;
			for (int i = 0; i < sds.length; i++) {
				Document doc = searcher.doc(sds[i].doc);
				System.out.println(sds[i].doc + ":" + doc.get("path") + "-->" + doc.get("filename"));
			}

			searcher.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
