package com.bt.tool;

/**
 * Created by xinyu.he on 2016/1/6.
 */
public abstract class MessagePostProcessor implements IPostProcessor<LogInfo> {

    private boolean enable = true;
    private IPostProcessor<LogInfo> mNextProcessor;

    public MessagePostProcessor() {
    }

    public MessagePostProcessor(IPostProcessor<LogInfo> next) {
        mNextProcessor = next;
    }

    public MessagePostProcessor(boolean enable) {
        this.enable = enable;
    }

    @Override
    public LogInfo postProcess(LogInfo info) {
        LogInfo result = info;
        if (enable && shouldProcess(info)) {
            result = process(info);
        }
        if (shouldProcessNext(info) && mNextProcessor != null) {
            return mNextProcessor.postProcess(result);
        }
        return result;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setNextProcessor(IPostProcessor<LogInfo> mNextProcessor) {
        this.mNextProcessor = mNextProcessor;
    }

    public boolean shouldProcessNext(LogInfo info) {
        return true;
    }

    abstract protected LogInfo process(LogInfo srcInfo);

    abstract public boolean shouldProcess(LogInfo info);
}
