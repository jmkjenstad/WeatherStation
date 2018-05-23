public class WeekStats
	{
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
		
		
		public float getMeanTemp()
		{
			return this.meanTemp;
		}
		
		public void setMeanTemp( float avg)
		{
			this.meanTemp = avg;
		}
		
		public float getMaxTemp()
		{
			return this.maxTemp;
		}
		
		public void setMaxTemp( float max, String date, String time )
		{
			this.maxTemp = max;
			this.maxTempDate = date;
			this.maxTempTime = time;
		}
		
		public String getMaxTempDate()
		{
			return this.maxTempDate;
		}
		
		
		public String getMaxTempTime()
		{
			return this.maxTempTime;
		}
		
		public float getLowTemp()
		{
			return this.minTemp;
		}
		
		public void setLowTemp( float low, String date, String time )
		{
			this.minTemp = low;
			this.minTempDate = date;
			this.minTempTime = time;
		}
		
		public String getLowTempDate()
		{
			return this.minTempDate;
		}
		
		public String getLowTempTime()
		{
			return this.minTempTime;
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
			this.windDate = date;
			this.windTime = time;
		}
		
		public String getMaxWindDate()
		{
			return this.windDate;
		}
		
		public String getMaxWindTime()
		{
			return this.windTime;
		}
		
		public float getRainfall()
		{
			return this.rainfall;
		}
		
		public void setRainfall( float rain )
		{
			this.rainfall = rain;
		}	
	
	}