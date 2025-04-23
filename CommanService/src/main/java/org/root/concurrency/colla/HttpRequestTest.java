package org.root.concurrency.colla;

import java.util.concurrent.*;

public class HttpRequestTest {

//    CompletableFuture.runAsync() :
//            .............................................................
//    If we want to run some background task asynchronously and do not want to return anything from that task,
//    It takes a Runnable object and returns CompletableFuture<Void>.

//            CompletableFuture.supplyAsync() :
//            ....................................................................
//            if we want to run some background task asynchronously and want to return anything from that task,
//    It takes a Supplier<T> and returns CompletableFuture<T> where T is the type of the value obtained by calling the given supplier.


//    Callback:(thenApply, thenAccept, thenRun)
//    thenApply():
//            ..........................
//    We can use thenApply() method to process and transform the result of a CompletableFuture when it arrives.
//    a function that accepts an argument of type T and produces a result of type R.
//
//    thenAccept():
//            .........................
//    If you do not want to return anything from the callback function and just want to run some piece of code after the completion of the CompletableFuture,
//    then we can use thenAccept() method.
//
//    thenAccept() and thenRun()methods are consumers and are often used as the last callback in the callback chain.

//    While thenAccept() has access to the result of the CompletableFuture on which it is attached,
//    thenRun() doesn’t even have access to the Future’s result. It takes a Runnable and returns CompletableFuture<Void>.


    public static void mainV1(String[] args) {

        Throwable throwable = new RuntimeException("Something went wrong!");
        CompletableFuture<String> failedFuture = CompletableFuture.failedFuture(throwable);
        Future<String> completableFuture = CompletableFuture.completedFuture("Complete");

        CompletableFuture<String> cf1 =
                failedFuture.handle((result, ex) -> {
                    if (ex != null) {
                        return "Recovered from \"" + ex.getMessage() + "\"";
                    } else {
                        return result;
                    }
                });

        CompletableFuture<String> cf2 =
                failedFuture.whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.out.println("Exception occurred");
                    } else {
                        System.out.println(result);
                    }
                });
        //handleAsync, whenCompleteAsync, and exceptionallyAsync. But exceptionallyAsyc is only available since Java 12.
        CompletableFuture<String> cf3 =
                failedFuture.exceptionally(ex -> {
                    return "Recovered from: \"" + ex.getMessage() + "\".";
                });

        cf3.thenAccept(System.out::println);
    }

//    Item	                handle()	whenComplete()	exceptionally()
//    Access to success?	Yes	         Yes	         No
//    Access to failure?	Yes	         Yes	         Yes
//    Can recover from failure?	Yes	     No	             Yes
//    Can transform result from T to U?	Yes	No	          No
//    Trigger when success?	Yes	Yes	                      No
//    Trigger when failure?	Yes	         Yes	          Yes
//    Has an async version?	Yes	         Yes	          Yes (Java 12)
//https://mincong.io/2020/05/30/exception-handling-in-completable-future
    //Logging=I want to ensure the result of my completable future to be logged because this is an important stage. But I don’t want to modify the result regardless of the situation
    //Exception-Only=I want to focus exclusively on exception handling and I don’t care about the normal result when the execution is successful.
    //Exception-Only Without Recovery=I want to focus exclusively on exception handling as above. However, I don’t want to recover from failure. By the way, I need to chain the current completable future with another stage by applying a funtion.
    //Transformation=Both normal result and exception are important for me and I need to transform the result type to the downstream.

    public static void sleep(int millis) {
        try {
            //Long duration = (long)(Math.random()*10); TimeUnit.SECONDS.sleep(duration);
            //Thread.sleep(Duration.of(RANDOM.nextInt(10, 1200), ChronoUnit.MILLIS));
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void mainV2(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Future<String> future = executorService.submit(new HttpRequest("httpClient", "httpRequest"));
        try {
            String data = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException");
        } catch (ExecutionException e) {
            System.err.println("ExecutionException");
        } catch (TimeoutException e) {
            System.err.println("TimeoutException");
        }
        executorService.shutdownNow();
        System.out.println("Main thread is doing other work...");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Callable<Void> task1 = () -> {
            while (true) {
                if (Thread.interrupted()) {
                    System.out.println("Interrupted, cleaning up and then throwing exception.");
                    throw new InterruptedException();
                }
                // Do work.
            }
        };

        final Callable<String> task2 = () -> {
            while (true) {
                if (Thread.interrupted()) {
                    Thread.currentThread().interrupt();
                    return "Canceled";
                }
                // Do work.
            }
        };
        Future<?> future = executorService.submit(task2);
        TimeUnit.SECONDS.sleep(1); // Wait for some time

        final boolean cancel = future.cancel(true);
        System.out.println("Is cancelled?: " + cancel);

        executorService.shutdown();
    }

}
