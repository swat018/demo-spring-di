package com.example.demospringdi;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class BookServiceTest {

//    BookService bookService = new BookServiceProxy(new DefaultBookService());
//    BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
//        new InvocationHandler() {
//            BookService bookService = new DefaultBookService();
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                if (method.getName().equals("rent")) {
//                    System.out.println("aaaa");
//                    Object invoke = method.invoke(bookService, args);
//                    System.out.println("bbbb");
//                    return invoke;
//                }
//                return method.invoke(bookService, args);
//            }
//        });

//    BookService bookService = new BookService();

    @Test
    public void di() throws Exception {
        // cglib
//        MethodInterceptor handler = new MethodInterceptor() {
//            BookService bookService = new BookService();
//            @Override
//            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//                if (method.getName().equals("rent")) {
//                    System.out.println("aaaa");
//                    Object invoke = method.invoke(bookService, args);
//                    System.out.println("bbbb");
//                    return invoke;
//                }
//                return method.invoke(bookService, args);
//            }
//        };
//        BookService bookService = (BookService) Enhancer.create(BookService.class, handler);

        Class<? extends BookService> proxyClass = new ByteBuddy().subclass(BookService.class)
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    BookService bookService = new BookService();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("aaaaa");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("bbbbb");
                        return invoke;
                    }
                }))
                .make().load(BookService.class.getClassLoader()).getLoaded();
        BookService bookService = proxyClass.getConstructor(null).newInstance();

        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
        bookService.returnBook(book);
    }

}