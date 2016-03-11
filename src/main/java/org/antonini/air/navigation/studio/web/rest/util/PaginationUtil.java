package org.antonini.air.navigation.studio.web.rest.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the <a href="https://developer.github.com/v3/#pagination">Github API</api>,
 * and follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link header)</a>.
 * </p>
 */
public class PaginationUtil {

    private static final String PAGE = "?page=";
    private static final String SIZE = "&size=";

    private PaginationUtil() {}

    public static HttpHeaders generatePaginationHttpHeaders(Page<?> page, String baseUrl)
        throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
        String link = "";
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link = "<" + (new URI(baseUrl + PAGE + (page.getNumber() + 1) + SIZE + page.getSize())).toString() + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getNumber()) > 0) {
            link += "<" + (new URI(baseUrl +PAGE + (page.getNumber() - 1) + SIZE + page.getSize())).toString() + ">; rel=\"prev\",";
        }
        // last and first link
        link += "<" + (new URI(baseUrl +PAGE + (page.getTotalPages() - 1) + SIZE + page.getSize())).toString() + ">; rel=\"last\",";
        link += "<" + (new URI(baseUrl +PAGE + 0 + SIZE + page.getSize())).toString() + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }
}
