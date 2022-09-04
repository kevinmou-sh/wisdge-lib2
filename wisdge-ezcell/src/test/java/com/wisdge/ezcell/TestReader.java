package com.wisdge.ezcell;

import com.wisdge.ezcell.context.AnalysisContext;
import com.wisdge.ezcell.event.SimpleEventListener;
import com.wisdge.ezcell.meta.Sheet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TestReader {

    @Test
    public void test() throws Exception {
        Map<String, Object> attr = new HashMap<>();
        attr.put("previewSize", 0);

        File file = new File("test.xlsx");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        SimpleEventListener listener = new SimpleEventListener();
        EzReader ezReader = new EzReader(bis, attr, listener);
        ezReader.read(new Sheet(1, 0));
        AnalysisContext content = ezReader.getAnalysisContext();
        log.debug("Rows: {}", content.getTotalRows());

        bis.close();
        fis.close();
    }
}
