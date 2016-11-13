package pl.harpi.samples.j2ee.demo.service.rest;

public final class RestUrls {
    private static final String SEGMENT_VERSION_1 = "v1";

    private static final String SEGMENT_REPOSITORY_RESOURCES = "repository";
    private static final String SEGMENT_PERSON_RESOURCE = "persons";
    private static final String SEGMENT_ADDRESS_RESOURCE = "addresses";

    /**
     * URL template for a single person: <i>repository/persons/{0:personId}</i>
     */
    public static final String[] URL_PERSON = {SEGMENT_VERSION_1, SEGMENT_REPOSITORY_RESOURCES, SEGMENT_PERSON_RESOURCE, "{0}"};

    /**
     * URL template for a single person: <i>repository/persons/{0:personId}/addresses</i>
     */
    public static final String[] URL_PERSON_ADDRESSES = {SEGMENT_VERSION_1, SEGMENT_REPOSITORY_RESOURCES,
            SEGMENT_PERSON_RESOURCE, "{0}", SEGMENT_ADDRESS_RESOURCE};
    /**
     * URL template for a single person: <i>repository/persons/{0:personId}/addresses/{1}:addresssId</i>
     */
    public static final String[] URL_PERSON_ADDRESS = {SEGMENT_VERSION_1, SEGMENT_REPOSITORY_RESOURCES,
            SEGMENT_PERSON_RESOURCE, "{0}", SEGMENT_ADDRESS_RESOURCE, "{1}"};
}
