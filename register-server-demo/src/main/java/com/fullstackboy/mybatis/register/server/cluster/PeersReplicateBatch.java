package com.fullstackboy.mybatis.register.server.cluster;

import com.fullstackboy.mybatis.register.server.web.AbstractRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 集群同步batch
 *
 * @author Liuyongfei
 * @date 2021/9/26 09:42
 */
public class PeersReplicateBatch {

    private List<AbstractRequest> requests = new ArrayList<>();

    public void add(AbstractRequest request) {
        this.requests.add(request);
    }

    public List<AbstractRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<AbstractRequest> requests) {
        this.requests = requests;
    }
}
