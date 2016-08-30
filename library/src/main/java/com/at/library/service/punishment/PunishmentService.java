package com.at.library.service.punishment;

import com.at.library.model.Punishment;

public interface PunishmentService {
	
	/**
	 * Crea un castigo para un usuario
	 * @param p
	 */
	void create(Punishment p);
}
