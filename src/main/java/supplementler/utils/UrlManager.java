package supplementler.utils;

import static supplementler.utils.ConfigManager.*;

public class UrlManager {


    public static String getTestUrl(String site) {
        String env = getTestUrlConfig();
        String siteUrl = null;

        if (env.equals("prod")){
            switch (site) {
                case "supplementler":
                    siteUrl = "https://www.supplementler.com";
                    break;
                case "vitaminler":
                    siteUrl = "https://www.vitaminler.com";
                    break;
                case "fitmoda":
                    siteUrl = "https://www.fitmoda.com";
                    break;
                case "msupplementler":
                    siteUrl = "https://m.supplementler.com";
                    break;
                case "mvitaminler":
                    siteUrl = "https://m.vitaminler.com";
                    break;
                case "mfitmoda":
                    siteUrl = "https://m.fitmoda.com";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + site);
            }
        }else {
            switch (site) {
                case "supplementler":
                    siteUrl = "https://" + env + "-www.supplementler.com";
                    break;
                case "vitaminler":
                    siteUrl = "https://" + env + "-www.vitaminler.com";
                    break;
                case "fitmoda":
                    siteUrl = "https://" + env + "-www.fitmoda.com";
                    break;
                case "msupplementler":
                    siteUrl = "https://" + env + "-m.supplementler.com";
                    break;
                case "mvitaminler":
                    siteUrl = "https://" + env + "-m.vitaminler.com";
                    break;
                case "mfitmoda":
                    siteUrl = "https://" + env + "-m.fitmoda.com";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + site);
            }
        }


        return siteUrl;
    }


}
