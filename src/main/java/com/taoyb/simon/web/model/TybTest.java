package com.taoyb.simon.web.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by taoyb on 2016-12-02.
 */
@SuppressWarnings("serial")
public class TybTest implements Serializable {
    private Long testId;//id
    private String testName;//用户名
    private String testEditor;//WYSIWYG编辑器
    private String testWbsr;
    private String testCzdh;
    private Integer testSzsr;
    private String testXlcd;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date testRqgs;

    public String getTestWbsr() {
        return testWbsr;
    }

    public TybTest setTestWbsr(String testWbsr) {
        this.testWbsr = testWbsr;
        return this;
    }

    public String getTestCzdh() {
        return testCzdh;
    }

    public TybTest setTestCzdh(String testCzdh) {
        this.testCzdh = testCzdh;
        return this;
    }

    public Integer getTestSzsr() {
        return testSzsr;
    }

    public TybTest setTestSzsr(Integer testSzsr) {
        this.testSzsr = testSzsr;
        return this;
    }

    public String getTestXlcd() {
        return testXlcd;
    }

    public TybTest setTestXlcd(String testXlcd) {
        this.testXlcd = testXlcd;
        return this;
    }

    public Date getTestRqgs() {
        return testRqgs;
    }

    public TybTest setTestRqgs(Date testRqgs) {
        this.testRqgs = testRqgs;
        return this;
    }

    public String getTestEditor() {
        return testEditor;
    }

    public void setTestEditor(String testEditor) {
        this.testEditor = testEditor;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
