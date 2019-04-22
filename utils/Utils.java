package utils;

public class Utils {

    /**
     * Determine if the request is static or not .
     *
     * @param uri
     * @return
     */
    public static boolean isStaticRequest(String uri) {
        try {
            int uriLength = uri.length();
            while (uriLength >= 0) {
                Character character = uri.charAt(uriLength - 1);
                if (character.equals(".")) {

                }
                uriLength--;
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
