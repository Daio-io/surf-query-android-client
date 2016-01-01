package io.daio.surfqueryclient;

import java.util.List;

public interface SurfResultTransformer {

    List<SurfQueryResult> transform(String jsonBody) throws SurfQueryException;

}
