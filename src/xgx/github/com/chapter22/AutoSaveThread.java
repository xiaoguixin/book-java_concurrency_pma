package xgx.github.com.chapter22;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 定期自动保存文档
 */
public class AutoSaveThread extends Thread {

    private final Document document;

    public AutoSaveThread(Document document) {
        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        while (true){
            try {
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (IOException | InterruptedException e){
                break;
            }
        }
    }
}
