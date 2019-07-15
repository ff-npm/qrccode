package com.reactlibrary.rns.almns;

/**
 */
abstract class PickPictureThread extends Thread implements Runnable {
    public abstract void pickPictureThreadRun();
    @Override
    public void run() {
        pickPictureThreadRun();
    }
}
