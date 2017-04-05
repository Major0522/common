package com.seagen.ecc.common.options;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 公共配置信息对象。
 * 
 * @author wutianbin
 *
 */
public class CommonOptions {

	protected String ejbPacket;
	
    protected Locale locale;

    private static CommonOptions options;
    
    public static CommonOptions getOptions() throws FileNotFoundException, 
    		IOException {
    	if (options == null) {
    		options = CommonOptionsReader.readOptions();
    	}
    	
    	return options; 
    }
    
    public String getEjbPacket() {
		return ejbPacket;
	}

	public void setEjbPacket(String ejbPacket) {
		this.ejbPacket = ejbPacket;
	}

	public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("commonOptions:{\n");
        buff.append("  locale:" + locale.toString() + "\n");
        buff.append("}");
        
        return buff.toString();
    }
    
    public static class Locale {
        protected String language;
        protected String country;
        protected String countryCodePhone;
        
        public Locale() {
			super();
		}

		public Locale(String language, String country, String countryCodePhone) {
			super();
			this.language = language;
			this.country = country;
			this.countryCodePhone = countryCodePhone;
		}

		public String getLanguage() {
            return language;
        }
        
        public void setLanguage(String language) {
            this.language = language;
        }
        
        public String getCountry() {
            return country;
        }
        
        public void setCountry(String country) {
            this.country = country;
        }
        
        public String getCountryCodePhone() {
            return countryCodePhone;
        }
        
        public void setCountryCodePhone(String countryCodePhone) {
            this.countryCodePhone = countryCodePhone;
        }

        @Override
        public String toString() {
            return "{ language:'" + language + "', " 
                    + "country:'" + country + "'," 
                    + "countryCodePhone:'" + countryCodePhone + "' }";
        }
        
    }
    
}
