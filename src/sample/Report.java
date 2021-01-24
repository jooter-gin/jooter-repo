package sample;

import java.sql.Timestamp;

public class Report {

    private int reportID;
    private int userID;
    private String reportText;
    private Timestamp submissionDate;
    private String reportTitle;
    private int destination;

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String title) {
        this.reportTitle = title;
    }

    public Report(){



    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }
}
