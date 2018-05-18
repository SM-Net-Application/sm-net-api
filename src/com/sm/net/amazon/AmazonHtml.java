package com.sm.net.amazon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for Amazon HTML code
 */
public class AmazonHtml {

	/*
	 * Metadata
	 */
	public static final String productTitleStart = "<span id=\"productTitle\" class=\"a-size-large\">";
	public static final String salePriceStart = "<span id=\"priceblock_saleprice\" class=\"a-size-medium a-color-price\">EUR";
	public static final String ourPriceStart = "<span id=\"priceblock_ourprice\" class=\"a-size-medium a-color-price\">EUR";
	public static final String imageTagWrapperStart = "<div id=\"imgTagWrapperId\" class=\"imgTagWrapper\">";

	/**
	 * 
	 * Returns the AmazonProduct-SimpleUrl: https://www.amazon.com/dp/B0034G4P7Q
	 * 
	 * @param country
	 * @return string of url
	 */
	public static String getAmazonProductSimpleUrl(String country, String productCode) {
		return "https://www.amazon." + country + "/dp/" + productCode;
	}

	/**
	 * 
	 * Returns the AmazonProductCode
	 * 
	 * For example:
	 * - url: https://www.amazon.de/gp/product/B072R24R1W/ref=s9u_psimh_gw_i2?ie=UTF8&pd_......
	 * - productCode: B072R24R1W
	 * 
	 * - url: https://www.amazon.de/dp/B01B68Q062/?coliid=I1YFSSHCQNA41P&colid=23SLIIDWCH......
	 * - productCode: B01B68Q062
	 * 
	 * @param url
	 * @return product code or empty
	 */
	public static String getProductCodeFromUrl(String url) {

		String productCode = "";

		if (url.length() > 0) {

			Pattern pattern = Pattern.compile("\\/dp\\/\\w+\\/");
			Matcher matcher = pattern.matcher(url);
			while (matcher.find()) {
				productCode = matcher.group();
				break;
			}

			if (productCode.length() > 0) {
				String[] strings = productCode.split("/");
				productCode = strings[2];
			} else {

				pattern = Pattern.compile("\\/product\\/\\w+\\/");
				matcher = pattern.matcher(url);
				while (matcher.find()) {
					productCode = matcher.group();
					break;
				}

				if (productCode.length() > 0) {
					String[] strings = productCode.split("/");
					productCode = strings[2];
				}
			}
		}
		return productCode;
	}
}
