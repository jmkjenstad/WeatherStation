import java.util.*;


public class Month
{
	private int month;
	private int year;
	private Hashtable<Integer, Day> dailySamples;
	private Hashtable<Integer, ArrayList<Day> > weeklySamples;
	
	private String prevailingWindDir;	
	
	private Hashtable<Integer, WeekStats> weekStats;
	
	
	private Hashtable< Integer, String > weekPrevailingWindDir;
	
	private float meanTemp;
	private float highTemp;
	private String hTempDate;
	private String hTempTime;
	private float lowTemp;
	private String lTempDate;
	private String lTempTime;
	private float meanWind;
	private float maxWind;
	private String mWindDate;
	private String mWindTime;
	private float rainfall;
	
	
	public Month()
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.weeklySamples = new Hashtable<Integer, ArrayList<Day> >();
		this.weekStats = new Hashtable< Integer, WeekStats>();
		this.month = -1;
		this.year = -1;
	}
	
	public Month( Month tempMonth )
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.weeklySamples = new Hashtable<Integer, ArrayList<Day> >();
		this.weekStats = new Hashtable< Integer, WeekStats>();
				
		this.year = tempMonth.getYear();
		this.month = tempMonth.getMonth();
		
		this.dailySamples.putAll( tempMonth.getAllDaySamples() );
		this.weeklySamples.putAll( tempMonth.getAllWeekSamples() ); 	

	}
	
	public String getPrevailingWindDir()
	{
		return this.prevailingWindDir;
	}
	
	public String getWeekPrevailingWindDir( int weekIndex )
	{
		String dir = this.weekPrevailingWindDir.get( weekIndex );
		
		return dir;
		
	}
	
	
	public int getSampleCount()
	{
		int count = 0;
		Hashtable<Integer, Day> daas = this.getAllDaySamples();
		
		// for each day in the month
		for( int i = 1; i <= daas.size(); i++ )
		{
			Day day = daas.get(i);
			if( day == null )
			{
				continue;
			}
			
			count += day.getSampleCount();  // sum the number of samples in each day			
		}
		
		return count;
	}
	
	
	public WeekStats getWeekStats( int weekIndex )
	{
		return this.weekStats.get(weekIndex);
	}
	
	
	public void calcWeekStats()
	{
		float tempSum = 0.0f;
		float sampCount = 0.0f;
		float windSum = 0.0f;
		float maxTemp = -50.0f;
		float minTemp = 1000.0f;
		String maxTempDate = "";
		String maxTempTime = "";
		String minTempDate = "";
		String minTempTime = "";
		float maxWind = 0.0f;
		String windDate = "";
		String windTime = "";
		float rainSum = 0.0f;
		float windGust = 0.0f;
		
		String north = "N";
		int nCount = 0;
		String east = "E";
		int eCount = 0;
		String south = "S";
		int sCount = 0;
		String west = "W";
		int wCount = 0;
		String nEast = "NE";
		int neCount = 0;
		String sEast = "SE";
		int seCount = 0;
		String sWest = "SW";
		int swCount = 0;
		String nWest = "NW";
		int nwCount = 0;
		
		int maxCount = -1;
		
	    Hashtable<Integer, ArrayList<Day> > weeks = this.getAllWeekSamples();  
		
		for( int i = 1; i <= weeks.size(); i++ )  // for all weeks in the month
		{
			ArrayList<Day> week = weeks.get(i);
			
			WeekStats stats = new WeekStats();
			
			for( int j = 0; j < week.size(); j++ )  // for each day in the week
			{
				Day day = week.get(j);
				
				if( day == null )  // if day doesn't exist, skip it
				{
					continue;
				}
				
				ArrayList<wItem> samples = day.getSamples();
				
				for( wItem item : samples )
				{
					sampCount += 1.0;  // count for upcoming mean calculation
			
					float temp = item.getTemperature();  // get relevant sample info to save later
					String tempDate = item.getDate();
					String tempTime = item.getTime();
			
					tempSum += temp;  // get running sum of temp for the day
			
					if( temp > maxTemp )  // find max temp for day
					{
						maxTemp = temp;
						maxTempDate = tempDate;
						maxTempTime = tempTime;
					}
			
					if( temp < minTemp )  // find min temp for day
					{		
						minTemp = temp;
						minTempDate = tempDate;
						minTempTime = tempTime;
					}
			
					temp = item.getWindspeed();  // get running sum of wind speed for the month
			
					windSum += temp;	

					temp = item.getWindgust();
			
					if( temp > maxWind )  // track max wind gust
					{
						maxWind = temp;
						windDate = tempDate;
						windTime = tempTime;
					}
			
					rainSum += item.getRainfall();  // accumulate rainfall
					
					String windDir = item.getWinddirection();
			
					// calculate prevailing wind direction
					if( north.equals( windDir ) )
					{
						nCount += 1;
						
						if( nCount > maxCount )
						{
							maxCount = nCount;
							this.weekPrevailingWindDir.put( i, "N" );
						}
					}
					else if( east.equals( windDir ) )
					{
						eCount +=1;
						
						if( eCount > maxCount )
						{
							maxCount = eCount;
							this.weekPrevailingWindDir.put( i, "E" );
						}
					}
					else if( south.equals( windDir ) )
					{
						sCount += 1;
						
						if( sCount > maxCount )
						{
							maxCount = sCount;
							this.weekPrevailingWindDir.put( i, "S" );
						}
					}
					else if( west.equals( windDir ) )
					{
						wCount +=1;
						
						if( wCount > maxCount )
						{
							maxCount = wCount;
							this.weekPrevailingWindDir.put( i, "W" );
						}
					}
					else if( nEast.equals( windDir ) )
					{
						neCount += 1;
						
						if( neCount > maxCount )
						{
							maxCount = neCount;
							this.weekPrevailingWindDir.put( i, "NE" );
						}
					}
					else if( sEast.equals( windDir ) )
					{
						seCount += 1;
						
						if( seCount > maxCount )
						{
							maxCount = seCount;
							this.weekPrevailingWindDir.put( i, "SE" );
						}
					}
					else if( sWest.equals( windDir ) )
					{
						swCount += 1;
						
						if( swCount > maxCount )
						{
							maxCount = swCount;
							this.weekPrevailingWindDir.put( i, "SW" );
						}
					}
					else if( nWest.equals( windDir ) )
					{
						nwCount += 1;
						
						if( nwCount > maxCount )
						{
							maxCount = nwCount;
							this.weekPrevailingWindDir.put( i, "NW" );
						}
					}			
				}
				
				stats.setMaxTemp( maxTemp, maxTempDate, maxTempTime );
				stats.setMeanTemp( (float) tempSum / sampCount );
				stats.setLowTemp( minTemp, minTempDate, minTempTime );
				stats.setMeanWind( (float) windSum / sampCount );
				stats.setMaxWind( maxWind, windDate, windTime );
				stats.setRainfall( rainSum );								
				
			}
			
			this.weekStats.put( i, stats );
		}
		
		
	}
	
	/**
     * **********************************************************************
     * Function: calcStats()
	 * Author: All 
	 * Description: calculates avg temp, high/low temp, avg wind speed, 
	 * max wind gust, and accumulated rainfall for the month
     * Parameters: n/a
     * **********************************************************************
     */
    public void calcStats()
	{
		float tempSum = 0.0f;
		float sampCount = 0.0f;
		float windSum = 0.0f;
		float maxTemp = -50.0f;
		float minTemp = 1000.0f;
		String maxTempDate = "";
		String maxTempTime = "";
		String minTempDate = "";
		String minTempTime = "";
		float maxWind = 0.0f;
		String windDate = "";
		String windTime = "";
		float rainSum = 0.0f;
		
		String north = "N";
		int nCount = 0;
		String east = "E";
		int eCount = 0;
		String south = "S";
		int sCount = 0;
		String west = "W";
		int wCount = 0;
		String nEast = "NE";
		int neCount = 0;
		String sEast = "SE";
		int seCount = 0;
		String sWest = "SW";
		int swCount = 0;
		String nWest = "NW";
		int nwCount = 0;
		
		int maxCount = -1;
		
		Hashtable<Integer, Day> days = this.getAllDaySamples();
		
		for( int i = 1; i <= days.size(); i++ )  // for each day in the month
		{
			Day daa = days.get( i );//dkey );
			
			if( daa == null )  // if day doesn't exist, skip to next day lookup
			{
				continue;
			}
				
			ArrayList<wItem> samples = daa.getSamples();
			
			for( wItem item: samples )  //for each sample
		    {
				sampCount += 1.0;  // count for upcoming mean calculation
			
				float temp = item.getTemperature();  // get relevant sample info to save later
				String tempDate = item.getDate();
				String tempTime = item.getTime();
			
				tempSum += temp;  // get running sum of temp for the day
			
				if( temp > maxTemp )  // find max temp for day
				{
					maxTemp = temp;
					maxTempDate = tempDate;
					maxTempTime = tempTime;
				}
			
				if( temp < minTemp )  // find min temp for day
				{
					minTemp = temp;
					minTempDate = tempDate;
					minTempTime = tempTime;
				}
			
				temp = item.getWindspeed();  // get running sum of wind speed for the month
			
				windSum += temp;
				
				temp = item.getWindgust();
			
				if( temp > maxWind )
				{
					maxWind = temp;
					windDate = tempDate;
					windTime = tempTime;
				}
			
				rainSum += item.getRainfall();	

				String windDir = item.getWinddirection();
			
				// calculate prevailing wind direction
				if( north.equals( windDir ) )
				{
					nCount += 1;
					
					if( nCount > maxCount )
					{
						maxCount = nCount;
						this.prevailingWindDir = "N";
					}
				}
				else if( east.equals( windDir ) )
				{
					eCount +=1;
					
					if( eCount > maxCount )
					{
						maxCount = eCount;
						this.prevailingWindDir = "E";
					}
				}
				else if( south.equals( windDir ) )
				{
					sCount += 1;
					
					if( sCount > maxCount )
					{
						maxCount = sCount;
						this.prevailingWindDir = "S";
					}
				}
				else if( west.equals( windDir ) )
				{
					wCount +=1;
					
					if( wCount > maxCount )
					{
						maxCount = wCount;
						this.prevailingWindDir = "W";
					}
				}
				else if( nEast.equals( windDir ) )
				{
					neCount += 1;
					
					if( neCount > maxCount )
					{
						maxCount = neCount;
						this.prevailingWindDir = "NE";
					}
				}
				else if( sEast.equals( windDir ) )
				{
					seCount += 1;
					
					if( seCount > maxCount )
					{
						maxCount = seCount;
						this.prevailingWindDir = "SE";
					}
				}
				else if( sWest.equals( windDir ) )
				{
					swCount += 1;
					
					if( swCount > maxCount )
					{
						maxCount = swCount;
						this.prevailingWindDir = "SW";
					}
				}
				else if( nWest.equals( windDir ) )
				{
					nwCount += 1;
					
					if( nwCount > maxCount )
					{
						maxCount = nwCount;
						this.prevailingWindDir = "NW";
					}
				}			
			
			}					
		}
		
		this.setHighTemp( maxTemp, maxTempDate, maxTempTime );  // set the max temp for month
			
		this.setLowTemp( minTemp, minTempDate, minTempTime );  // set low temp
		
		this.setMaxWind( maxWind, windDate, windTime );  // set max wind speed
		
		this.setMeanWind( (float) windSum / sampCount );  // set avg wind speed
		
		this.setMeanTemp( (float) tempSum / sampCount ); // set avg temp speed 
		
		this.setRainfall( rainSum );  // set accumulated precipitation		
		
	}
	
	
	public float getMeanTemp()
	{
		return this.meanTemp;
	}
	
	public void setMeanTemp( float avg )
	{
		this.meanTemp = avg;
	}
	
	public float getHighTemp()
	{
		return this.highTemp;
	}
	
	public void setHighTemp( float high, String date, String time )
	{
		this.highTemp = high;
		this.hTempDate = date;
		this.hTempTime = time;
	}
	
	public String getHighTempDate()
	{
		return this.hTempDate;
	}
	
	public String getHighTempTime()
	{
		return this.hTempTime;
	}
	
	public float getLowTemp()
	{
		return this.lowTemp;
	}
	
	public void setLowTemp( float high, String date, String time )
	{
		this.lowTemp = high;
		this.lTempDate = date;
		this.lTempTime = time;
	}
	
	public String getLowTempDate()
	{
		return this.lTempDate;
	}
	
	public String getLowTempTime()
	{
		return this.lTempTime;
	}
	
	public float getMeanWind()
	{
		return this.meanWind;
	}
	
	public void setMeanWind( float avg )
	{
		this.meanWind = avg;
	}
	
	public float getMaxWind()
	{
		return this.maxWind;
	}
	
	public void setMaxWind( float max, String date, String time )
	{
		this.maxWind = max;
		this.mWindDate = date;
		this.mWindTime = time;
	}
	
	public String getMaxWindDate()
	{
		return this.mWindDate;
	}
	
	public String getMaxWindTime()
	{
		return this.mWindTime;
	}
	
	public float getRainfall()
	{
		return this.rainfall;
	}
	
	public void setRainfall( float rain )
	{
		this.rainfall = rain;
	}
	
	
	
	public int getNumberOfDays()
	{
		return this.dailySamples.size();
	}
	
	
	
	public void setWeeklySamples()
	{
		
		int numOdays = this.dailySamples.size();  // number of days in the month
		
		ArrayList<Day> daze = new ArrayList<Day>();
		
		int weekIndex = 1;

		
		for( int i = 1; i <= numOdays; i++ )  // for each day in the month
		{
			Day daa = this.dailySamples.get(i);
			
			daze.add( daa );  // add the day to our week 
			
			boolean flag = false;  // flag tells us if there is a fifth week in the month
			
			if( i % 7 == 0 ) // if we have processed seven days, consider this a week
			{

				ArrayList<Day> temp = new ArrayList<Day>(daze);
				this.weeklySamples.put( weekIndex, temp );
				
				daze.clear(); 
				weekIndex += 1;
				flag = true;
			}
			
			if( i == numOdays && !flag ) // if we have processed the last day in the fifth week of a month
			{
				// add the last incomplete week to the week list
				this.weeklySamples.put( weekIndex, daze );
				
			}												
		}	

	}
	
	
	public Hashtable<Integer, ArrayList<Day> > getAllWeekSamples()
	{
		return this.weeklySamples;
	}
	
	public ArrayList<Day> getWeekOfSamples( int weekKey )
	{
		return this.weeklySamples.get( weekKey );
	}
	
	
	public int getMonth()
	{
		return this.month;
	}
	
	public void setMonth( int tempMonth)
	{
		this.month = tempMonth;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public void setYear( int tempYear )
	{
		this.year = tempYear;
	}
	
	public void setDailySamples( int dayKey, Day dayOfSamples )
	{		
		this.dailySamples.put( dayKey, dayOfSamples );
	}
	
	public Hashtable<Integer, Day> getAllDaySamples()
	{
		return this.dailySamples;
	}
	
	public Day getDayofSamples( int dayKey)
	{
		return this.dailySamples.get(dayKey);
	}
	
	public void reset()
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.month = -1;
		this.year = -1;
	}
	
	
}
