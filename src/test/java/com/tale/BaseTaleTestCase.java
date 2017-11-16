package com.tale;

import com.blade.Blade;
import com.mashape.unirest.http.Unirest;
import com.tale.controller.InstallController;
import org.junit.After;
import org.junit.Before;

/**
 * Created by wangke on 2017/11/13.
 */
public class BaseTaleTestCase {

    protected Blade app;
    private   String origin    = "http://127.0.0.1:10086";
    protected String firefoxUA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:53.0) Gecko/20100101 Firefox/53.0";

    @Before
    public void setup() throws Exception {
        app = Blade.me();
        Unirest.setTimeouts(30_000, 10_000);
    }

    protected void start() {
        Blade.me().listen(10086).start(InstallController.class).start();
    }

    protected void start(Blade blade) {
        blade.listen(10086).start(InstallController.class).start();
    }

    @After
    public void after() {
        app.stop();
        app.await();
    }
}
