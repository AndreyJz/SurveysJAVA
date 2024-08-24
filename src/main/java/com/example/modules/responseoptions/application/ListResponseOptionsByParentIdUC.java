package com.example.modules.responseoptions.application;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

import java.util.List;

public class ListResponseOptionsByParentIdUC {
    private final ResponseOptionsService responseOptionsService;

    public ListResponseOptionsByParentIdUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public List<ResponseOptions> list(int parentId) { return this.responseOptionsService.listResponseOptionsByParentId(parentId); }
}
