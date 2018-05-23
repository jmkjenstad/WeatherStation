package weather.station;


/************************************************************************
    Class:  WeekStats
    Author: All
    Description: This class creates data for the weekly statistics
    * computed for the statistics menu item. When on the weekly graph tab,
    * selecting stats will calculate stats only for a week.
    Parameters: None
 ************************************************************************/
public class WeekStats {
    
    //establishes private member data
    private float meanTemp;
    private float maxTemp;
    private String maxTempDate;
    private String maxTempTime;
    private float minTemp;
    private String minTempDate;
    private String minTempTime;
    private float meanWind;
    private float maxWind;
    private String windDate;
    private String windTime;
    private float rainfall;
    
    /************************************************************************
    Function:  getMeanTemp
    Author: All
    Description: This function is a getter returning the mean temp for
    * a week of data.
    Parameters: None
    ************************************************************************/
    public float getMeanTemp() {
        return this.meanTemp;
    }
    
    /************************************************************************
    Function:  setMeanTemp
    Author: All
    Description: This function is a setter for the mean of the week data.
    Parameters: avg - average for the week
    ************************************************************************/
    public void setMeanTemp(float avg) {
        this.meanTemp = avg;
    }
    
    /************************************************************************
    Function:  getMaxTemp
    Author: All
    Description: A setter function to return the max temp for the week.
    Parameters: None
    ************************************************************************/
    public float getMaxTemp() {
        return this.maxTemp;
    }
    
    /************************************************************************
    Function:  getMaxTemp
    Author: All
    Description: A setter function to return the max temp for the week.
    Parameters: None
    ************************************************************************/
    public void setMaxTemp(float max, String date, String time) {
        this.maxTemp = max;
        this.maxTempDate = date;
        this.maxTempTime = time;
    }

    /************************************************************************
    Function:  getMaxTempDate
    Author: All
    Description: A getter function to return the max temp date for the week.
    Parameters: None
    ************************************************************************/
    public String getMaxTempDate() {
        return this.maxTempDate;
    }

    /************************************************************************
    Function:  getMaxTempTime
    Author: All
    Description: A getter function to return the max temp time for the week.
    Parameters: None
    ************************************************************************/
    public String getMaxTempTime() {
        return this.maxTempTime;
    }

    /************************************************************************
    Function:  getLowTemp
    Author: All
    Description: A getter function to return the min temp for the week.
    Parameters: None
    ************************************************************************/
    public float getLowTemp() {
        return this.minTemp;
    }

    /************************************************************************
    Function:  setLowTemp
    Author: All
    Description: A setter function set the temp low, date, and time for the
    * week.
    Parameters: low  - the low temp
    *           date - the date of the low temp
    *           time - the time of the low temp
    ************************************************************************/
    public void setLowTemp(float low, String date, String time) {
        this.minTemp = low;
        this.minTempDate = date;
        this.minTempTime = time;
    }

    /************************************************************************
    Function:  getLowTempDate
    Author: All
    Description: A getter function to return the min temp date for the week.
    Parameters: None
    ************************************************************************/
    public String getLowTempDate() {
        return this.minTempDate;
    }

    /************************************************************************
    Function:  getLowTempDate
    Author: All
    Description: A getter function to return the low temp date for the week.
    Parameters: None
    ************************************************************************/
    public String getLowTempTime() {
        return this.minTempTime;
    }

    /************************************************************************
    Function:  getMeanWind
    Author: All
    Description: Getter function to return the mean wind speed for the week.
    Parameters: None
    ************************************************************************/
    public float getMeanWind() {
        return this.meanWind;
    }

    /************************************************************************
    Function:  setMeanWind
    Author: All
    Description: A setter function to set the mean wind speed for the week.
    Parameters: None
    ************************************************************************/
    public void setMeanWind(float avg) {
        this.meanWind = avg;
    }

    /************************************************************************
    Function:  getMaxWind
    Author: All
    Description: A getter function to return the max wind speed for the week.
    Parameters: None
    ************************************************************************/
    public float getMaxWind() {
        return this.maxWind;
    }

    /************************************************************************
    Function:  setMaxWind
    Author: All
    Description: A setter function to set the max wind speed for the week.
    Parameters: None
    ************************************************************************/
    public void setMaxWind(float max, String date, String time) {
        this.maxWind = max;
        this.windDate = date;
        this.windTime = time;
    }

    /************************************************************************
    Function:  getMaxWindDate
    Author: All
    Description: A getter function to return the max wind date for the week.
    Parameters: None
    ************************************************************************/
    public String getMaxWindDate() {
        return this.windDate;
    }

    /************************************************************************
    Function:  getMaxWindTime
    Author: All
    Description: A getter function to return the max wind time for the week.
    Parameters: None
    ************************************************************************/
    public String getMaxWindTime() {
        return this.windTime;
    }

    /************************************************************************
    Function:  getRainfall
    Author: All
    Description: A getter function to return the total rainfall for the week.
    Parameters: None
    ************************************************************************/
    public float getRainfall() {
        return this.rainfall;
    }

    /************************************************************************
    Function:  setRainfall
    Author: All
    Description: A setter function to set the total rainfall for the week.
    Parameters: None
    ************************************************************************/
    public void setRainfall(float rain) {
        this.rainfall = rain;
    }
}
