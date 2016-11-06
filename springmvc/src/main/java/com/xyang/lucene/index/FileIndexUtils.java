package com.xyang.lucene.index;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class FileIndexUtils extends IndexUtil {
	private static Directory directory = null;
	static {
		try {
			directory = FSDirectory.open(new File(index_save_dir+"_file"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Directory getDirectory() {
		return directory;
	}

	public static void createIndex(boolean hasNew) {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
			if (hasNew) {
				writer.deleteAll();
			}
			File file = new File(parseFile_save_dir);
			Document doc = null;
			for (File f : file.listFiles()) {
				doc = new Document();
				doc.add(new Field("content", new FileReader(f)));
				doc.add(new Field("filename", f.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("path", f.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new NumericField("date", Field.Store.YES, true).setLongValue(f.lastModified()));
				doc.add(new NumericField("size", Field.Store.YES, true).setIntValue((int) (f.length() / 1024)));
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
}
