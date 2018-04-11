package com.jking.computersite.service;

import com.jking.computersite.entity.Committee;
import com.jking.computersite.entity.Leader;
import com.jking.computersite.entity.Union;

import java.util.List;
import java.util.Map;

public interface LeaderService  {
List<Committee>work();

List<Union>union();

Map institution();
}
