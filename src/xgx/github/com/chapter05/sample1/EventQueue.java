package xgx.github.com.chapter05.sample1;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * 单线程通信（生产者消费者模型）
 * 演示wait和notify
 */
public class EventQueue {

    private final int max;

    private final static int DEFAULT_MAX_EVENT = 10;

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    static class Event{}

    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max){
        this.max = max;
    }

    /**
     * 增加到队列中
     * @param event
     */
    public void offer(Event event){
        synchronized (eventQueue){
            if(eventQueue.size()>=max){// 多线程替换为while
                try{
                    console(" the queue is full.");
                    eventQueue.wait();// 进入阻塞
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            console(" the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();// 唤醒单个阻塞线程，多线程替换为notifyAll
        }
    }

    /**
     * 从队列中拿出元素
     * @return
     */
    public Event take(){
        synchronized (eventQueue){
            if(eventQueue.isEmpty()){// 多线程替换为while
                try {
                    console(" the queue is empty.");
                    eventQueue.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            Event event = eventQueue.removeFirst();
            eventQueue.notify();// 多线程替换为notifyAll
            console(" the event "+event+" is handled.");
            return event;
        }
    }

    /**
     * 打印信息
     * @param message
     */
    private void console(String message){
        System.out.printf("%s:%s\n",currentThread().getName(),message);
    }

}
