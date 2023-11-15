import {applyErrorMessage, clearErrors, parseAppropriateFloat, validateFormData} from "./util/Utils.js";
import {Canvas} from "./util/Canvas.js";

const canvas = new Canvas("area")

$(window).on('load', () => {
	canvas.drawEmptyArea()
})

$(document).ready(() => {
	const form = document.querySelector('form')

	let currentData = {
		x: undefined,
		y: undefined,
		r: undefined
	}

	function validateInputs(data) {
		let validationResult = validateFormData(data)
		if (!validationResult.result)
		{
			applyErrorMessage(validationResult.message)
			return
		}

		clearErrors()
		$("<input type='hidden' name='x'/>")
			.attr('value', data.x)
			.prependTo("form")

		$("<input type='hidden' name='y'/>")
			.attr('value', data.y)
			.prependTo("form")

		$("<input type='hidden' name='r'/>")
			.attr('value', data.r)
			.prependTo("form")

		form.submit()
	}


	$("input[type='button'][name='x']").click((e) => {
		currentData.x = parseInt($(e.target).val())
	})

	$("#y").on('change keyup paste', (e) => {

		if (($(e.target).val().search('.')) && $(e.target).val().replace(",",".").split(".")[1].length >=7){
			$(e.target).val('')
		}
		currentData.y = parseAppropriateFloat($(e.target).val().replace(",","."))
	})

	$("input[type='checkbox'][name='r']").click((e) => {
		$("input[type='checkbox'][name='r']").prop('checked', false)
		$(e.target).prop('checked', true)
		currentData.r = parseAppropriateFloat($(e.target).val())
		if (!isNaN(currentData.r)) {
			canvas.redrawArea($(e.target).val(), true)
			canvas.applyPoints(calculatedPoints)
		}
		else
		{
			canvas.redrawArea($(e.target).val(), false)
		}
	})

	canvas.onClick(
		(success) => {
			clearErrors()
			validateInputs(success)
		},
		(error) => {
			applyErrorMessage(error)
		}
	)

	$("#do-check").click(() => validateInputs(currentData))
})