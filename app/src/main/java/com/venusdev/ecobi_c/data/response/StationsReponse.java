/**
 * Response StationsReponse
 *
 * Se adapta el reponse obtenido por la API.
 * Consta de un elemento DataResponse llamado "network".
 *
 * @author Jair Migliolo
 * @version 2020.1103
 * @since 1.0
 */

package com.venusdev.ecobi_c.data.response;

import com.venusdev.ecobi_c.model.DataResponse;

public class StationsReponse {

    DataResponse network;

    public DataResponse getNetwork() {
        return network;
    }

    public void setNetwork(DataResponse network) {
        this.network = network;
    }
}
