package com.pwc.assesment.model.dataHandlers;

import com.pwc.assesment.model.entities.EntityModel;

public interface DataHandlerModel {
	
	public void save(EntityModel entity);
	
	public EntityModel load(int id);

}
