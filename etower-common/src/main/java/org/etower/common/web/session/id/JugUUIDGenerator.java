package org.etower.common.web.session.id;

import org.apache.commons.lang3.StringUtils;
import org.safehaus.uuid.UUID;
import org.safehaus.uuid.UUIDGenerator;

/**
 * 通过UUID生成SESSION ID
 * 
 * @author dawei.li
 *
 */
public class JugUUIDGenerator implements SessionIdGenerator {

	public String get() {
		UUID uuid = UUIDGenerator.getInstance().generateRandomBasedUUID();
		return StringUtils.remove(uuid.toString(), '-');
	}

	public static void main(String[] args) {
		JugUUIDGenerator j = new JugUUIDGenerator();
		UUIDGenerator.getInstance().generateRandomBasedUUID();
		long time = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			UUIDGenerator.getInstance().generateRandomBasedUUID();
		}
		time = System.currentTimeMillis() - time;
		System.out.println(time);
		System.out.println(j.get());
	}
}
