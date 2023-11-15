package com.mitrian.validation;

import com.mitrian.dto.PointCheckDTO;
import com.mitrian.exception.InvalidAreaSizeException;
import com.mitrian.exception.InvalidPointCoordinatesException;
import com.mitrian.exception.NullValidationTargetException;
import com.mitrian.exception.ValidationException;

import java.util.List;

public class CoordinatesRequestValidator implements Validator<PointCheckDTO> {

	private final List<Integer> VALID_X_COORDS = List.of(-3, -2, -1, 0, 1, 2, 3, 4, 5);
	private final List<Float> VALID_R_VALUES = List.of(1F, 1.5F, 2F, 2.5F, 3F);
	private final Float LOWER_Y_BOUND = -5F;
	private final Float HIGHER_Y_BOUND = 3F;

	@Override
	public void validate(PointCheckDTO obj) throws ValidationException {
		if (obj == null)
			throw new NullValidationTargetException("Something went wrong: validation target does not exist");

		if (obj.x() == null || !VALID_X_COORDS.contains(obj.x()))
			throw new InvalidPointCoordinatesException("Something went wrong: x is out of valid range");

		if (obj.y() == null || (obj.y() <= LOWER_Y_BOUND || obj.y() >= HIGHER_Y_BOUND))
			throw new InvalidPointCoordinatesException("Something went wrong: y is out of valid range");

		if (obj.r() == null || !VALID_R_VALUES.contains(obj.r()))
			throw new InvalidAreaSizeException("Something went wrong: r is out of valid range");
	}
}
