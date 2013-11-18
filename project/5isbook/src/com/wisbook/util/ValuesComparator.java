package com.wisbook.util;

import java.util.Comparator;
import com.wisbook.model.cms.EntityValue;

public class ValuesComparator implements Comparator<EntityValue>{

	@Override
	public int compare(EntityValue o1, EntityValue o2) {
		// TODO Auto-generated method stub
		System.out.println(o1.getId()+"'''''''''''''''''");
		System.out.println(o2.getId()+"=================");
		if(o1.getId()>o2.getId())
			return 1;
		else
			return -1;
	}

}

