package com.gydoc.xmh.functest.login;

import com.gydoc.xmh.uitest.spike.UITest;
import java.io.File;
import junit.extensions.abbot.ScriptFixture;
import junit.extensions.abbot.ScriptTestSuite;
import junit.framework.Test;

/**
 *
 */
public class Test_Login extends ScriptFixture {
    
    public Test_Login(String filename) {
        super(filename);
    }
    
    public static Test suite() {
        return new ScriptTestSuite(Test_Login.class,
                                   "test/com/gydoc/xmh/functest/login/") {
            public boolean accept(File file) {
                return super.accept(file)
                    && !file.getName().equals("fixture.xml");
            }
        };
    }
    
}
