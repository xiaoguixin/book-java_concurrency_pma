package xgx.github.com.chapter22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.currentThread;

/**
 * Balking（犹豫设计模式）模式之文档编辑
 * 多个线程监控某个共享变量，A、B线程都监控到了共享变量发生了，如果B发现A已经开始行动了，线程B放弃执行
 */
public class Document {

    // 文档发生改变changed为true
    private boolean changed = false;

    // 一次需要保存文档的内存，可以理解为文档缓存
    private List<String> content = new ArrayList<>();

    private final FileWriter writer;

    // 自动保存文档对线程
    private static AutoSaveThread autoSaveThread;

    /**
     * 构造函数输入文档路径和文档名称
     * @param documentPath
     * @param documentName
     * @throws IOException
     */
    private Document(String documentPath, String documentName) throws IOException {
        this.writer = new FileWriter(new File(documentPath,documentName));
    }

    /**
     * 主要用于创建文档，然后启动自动保存文档的线程
     * @param documentPath
     * @param documentName
     * @return
     * @throws IOException
     */
    public static Document create(String documentPath, String documentName) throws IOException {
        Document document = new Document(documentPath,documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    /**
     * 文档编辑，content队列中加入内容
     * @param content
     */
    public void edit(String content){
        synchronized (this){
            this.content.add(content);
            this.changed = true;
        }
    }

    /**
     * 关闭的时候，先中断自动保存线程，然后关闭writer线程
     * @throws IOException
     */
    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }

    /**
     *
     * @throws IOException
     */
    public void save() throws IOException {
        synchronized (this){
            if(!changed){// 文档未变化或者已经保存直接返回
                return;
            }

            //
            System.out.println(currentThread()+" execute the save action.");
            for (String cacheLine : content){
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
            }

            this.writer.flush();
            this.changed = false;
            this.content.clear();
        }
    }
}
