package pl.harpi.samples.j2ee.demo.service.rest;

public final class RestUrls {
    private static final String SEGMENT_VERSION_1 = "v1";

    private static final String SEGMENT_REPOSITORY_RESOURCES = "repository";
    private static final String SEGMENT_PERSON_RESOURCE = "persons";

    /**
     * URL template for a single person: <i>repository/persons/{0:personId}</i>
     */
    public static final String[] URL_PERSON = {SEGMENT_VERSION_1, SEGMENT_REPOSITORY_RESOURCES, SEGMENT_PERSON_RESOURCE, "{0}"};
}
