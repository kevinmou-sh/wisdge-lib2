package com.wisdge.ezcell.analysis;

import com.wisdge.ezcell.context.AnalysisContext;
import com.wisdge.ezcell.meta.Sheet;

import java.util.List;

public interface ExcelAnalyser {

    /**
     * parse one sheet
     *
     * @param sheetParam
     */
    void analysis(Sheet sheetParam) throws Exception;

    /**
     * parse all sheets
     */
    void analysis() throws Exception;
    
    /**
     * Get Context of Analyzer
     * @return AnalysisContext
     */
    AnalysisContext getContext();

    /**
     * get all sheet of workbook
     *
     * @return all sheets
     */
    List<Sheet> getSheets() throws Exception;

}
