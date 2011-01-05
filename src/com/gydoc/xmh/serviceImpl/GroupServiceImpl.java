package com.gydoc.xmh.serviceImpl;

import com.gydoc.xmh.domain.Group;
import com.gydoc.xmh.service.GroupService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation= Propagation.REQUIRED)
public class GroupServiceImpl extends ServiceBase implements GroupService {
    
    public Group add(Group g) {
        getCS().save(g);
        return g;
    }
    
}
