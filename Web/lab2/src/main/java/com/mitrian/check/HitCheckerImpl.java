package com.mitrian.check;

import com.mitrian.dto.PointCheckDTO;

public class HitCheckerImpl implements HitChecker {
	@Override
	public boolean check(PointCheckDTO data) {
//		(1)
		if (data.x() >= 0 && data.y() >= 0)
			return (data.x() * data.x() + data.y() * data.y() <= data.r() * data.r());

//		(2)
		if (data.x() <= 0 && data.y() >= 0)
			return (data.x() >= -data.r() && data.y() <= data.r() / 2);

//		(4)
		if (data.x() >= 0 && data.y() <= 0)
			return (data.y() >= data.x() - data.r());

		return false;
	}
}
