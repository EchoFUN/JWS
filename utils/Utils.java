package utils;

public class Utils {

    /**
     * Determine if the request is static or not .
     *
     *
     *
     *
     *
     * @param uri
     * @return
     */
    public static boolean isStaticRequest(String uri) {

        int uriLength = uri.length();
        while (uriLength >= 0) {
            Character character = uri.charAt(uriLength);
            if (character.equals(".")) {

            }



            uriLength--;
        }

        return false;
    }
}
