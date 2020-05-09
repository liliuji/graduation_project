package com.service;


import com.domain.Enlist;

import java.util.List;

public interface EnlistService {

    List<Enlist> getEnlists();

    int updateEnlistStatus(String enlistStatus,int enlistId);

    int deleteEnlistById(int enlistId);

    int saveEnlist(Enlist enlist);
}
