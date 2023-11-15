/**
 * Class for better canvas using experience.
 *
 */
class Canvas {

	/**
	 * Canvas class constructor.
	 *
	 * @param id canvas element id
	 */
	constructor(id) {
		this.canvas = document.getElementById(id)
		this.ctx = this.canvas.getContext("2d")
		this.dynamicScaling = 0

		this.setCanvasDPI()
	}

	setCanvasDPI() {
		let dpi = $(window).devicePixelRatio
		let height = +getComputedStyle(this.canvas).getPropertyValue('height').slice(0, -2)
		let width = +getComputedStyle(this.canvas).getPropertyValue('width').slice(0, -2)

		this.canvas.setAttribute('height', `${height * dpi}`)
		this.canvas.setAttribute('width', `${width * dpi}`)
	}

	/**
	 * This function clears canvas.
	 */
	clear() {
		this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
	}

	/**
	 * This function draws X & Y axes of cartesian coordinate system.
	 *
	 * @param x1 begin X
	 * @param y1 begin Y
	 * @param x2 end X
	 * @param y2 end Y
	 */
	drawAxis(x1, y1, x2, y2) {
		let { canvas, ctx } = this
		const headLength = 10
		const angle = Math.atan2(y2 - y1, x2 - x1)

		ctx.beginPath()
		ctx.moveTo(x1, y1)
		ctx.lineTo(x2, y2)
		ctx.lineTo(x2 - headLength * Math.cos(angle - Math.PI / 6), y2 - headLength * Math.sin(angle - Math.PI / 6));
        ctx.moveTo(x2, y2);
        ctx.lineTo(x2 - headLength * Math.cos(angle + Math.PI / 6), y2 - headLength * Math.sin(angle + Math.PI / 6));
        ctx.stroke();
	}

	/**
	 * This function draws canvas ticks.
	 *
	 * @param size area size
	 */
	drawTicks(size) {
		let { ctx, canvas } = this

	    // X-axis ticks
	    const tickLength = 5;
	    for (let tickValue = -size; tickValue <= size; tickValue += size / 2) {
	        const xTickPosition = canvas.width / 2 + tickValue * this.dynamicScaling;
	        ctx.beginPath();
	        ctx.moveTo(xTickPosition, canvas.height / 2 - tickLength / 2);
	        ctx.lineTo(xTickPosition, canvas.height / 2 + tickLength / 2);
	        ctx.stroke();
	    }

	    // Y-axis ticks
	    for (let tickValue = -size; tickValue <= size; tickValue += size / 2) {
	        const yTickPosition = canvas.height / 2 - tickValue * this.dynamicScaling;
	        ctx.beginPath();
	        ctx.moveTo(canvas.width / 2 - tickLength / 2, yTickPosition);
	        ctx.lineTo(canvas.width / 2 + tickLength / 2, yTickPosition);
	        ctx.stroke();
	    }
	}

	/**
	 * This function draws labels based on current area size.
	 *
	 * @param size area size
	 * @param userInputExists user input existence
	 * @param color label colors
	 */
	drawLabels(size, userInputExists, color = "#000000") {
		const labelSize = userInputExists ? size : "R"
		const labelSizeHalf = userInputExists ? size / 2 : "R/2"
		let { ctx, canvas } = this
		const xOffset = 20
		const yOffset = 5

		ctx.fillStyle = color

	// 	X axis labels
		ctx.fillText('-' + labelSize, canvas.width / 2 - size * this.dynamicScaling, canvas.height / 2 + xOffset)
		ctx.fillText('-' + labelSizeHalf, canvas.width / 2 - (size / 2) * this.dynamicScaling, canvas.height / 2 + xOffset)
		ctx.fillText(labelSize, canvas.width / 2 + size * this.dynamicScaling, canvas.height / 2 + xOffset)
		ctx.fillText(labelSizeHalf, canvas.width / 2 + (size / 2) * this.dynamicScaling, canvas.height / 2 + xOffset)

	// 	Y axis labels
		ctx.fillText(labelSize, canvas.width / 2 + yOffset, canvas.height / 2 - size * this.dynamicScaling)
		ctx.fillText(labelSizeHalf, canvas.width / 2 + yOffset, canvas.height / 2 - (size / 2) * this.dynamicScaling)
		ctx.fillText('-' + labelSize, canvas.width / 2 + yOffset, canvas.height / 2 + size * this.dynamicScaling)
		ctx.fillText('-' + labelSizeHalf, canvas.width / 2 + yOffset, canvas.height / 2 + (size / 2) * this.dynamicScaling)
	}

	/**
	 * This function draws semicircle in the 1 cartesian quarter.
	 *
	 * @param R semicircle radius
	 * @param color area color
	 */
	drawSemiCircle(R, color = "#0098fa30") {
		let { ctx, canvas } = this

		ctx.fillStyle = color
		ctx.beginPath()
		ctx.arc(canvas.width / 2, canvas.height / 2, R * this.dynamicScaling, 0, -Math.PI / 2, true)
		ctx.lineTo(canvas.width / 2, canvas.height / 2)
		ctx.closePath()
		ctx.fill()
	}

	/**
	 * This function draws a triangle in the 2 cartesian quarter.
	 *
	 * @param R triangle size
	 * @param color triangle color
	 */
	drawTriangle(R, color = "#0098fa30") {
		let { ctx, canvas } = this

		ctx.beginPath()
		ctx.moveTo(canvas.width / 2, canvas.height / 2)
		ctx.lineTo(canvas.width / 2, canvas.height / 2 + R * this.dynamicScaling)
		ctx.lineTo(canvas.width / 2 + R * this.dynamicScaling, canvas.height / 2)
		ctx.closePath()
		ctx.fill()
	}

	/**
	 * This function draws a rectangle in 3 cartesian quarter.
	 *
	 * @param R rectangle size
	 * @param color rectangle color
	 */
	drawRectangle(R, color = "#0098fa30") {
		let { ctx, canvas } = this
	    ctx.fillRect(canvas.width / 2, canvas.height / 2, -R * this.dynamicScaling, -R / 2 * this.dynamicScaling);
	}

	/**
	 * This function draws simple cartesian coordinate system with no areas on it.
	 *
	 */
	drawCartesian(size, userInputExists) {
		let { canvas } = this

		// Drawing X & Y axes with ticks on them
		this.ctx.lineWidth = 1
		this.drawAxis(0, canvas.height / 2, canvas.width, canvas.height / 2)
		this.drawAxis(canvas.width / 2, canvas.height, canvas.width / 2, 0)
		this.drawTicks(size)
		this.drawLabels(size, userInputExists)
	}

	/**
	 * This function draws simple cartesian coordinate system with no-user-input labels.
	 *
	 * @param size area size
	 */
	drawEmptyArea(size = 3) {
		let { ctx, canvas } = this

		// Clear canvas
		this.clear()

		let baseScaling = canvas.width / 6
		this.dynamicScaling = baseScaling / size

		// Drawing X & Y axes with ticks on them
		this.drawCartesian(size, false)
	}

	/**
	 * This function draws cartesian coordinates system.
	 *
	 */
	drawArea(size, userInputExists) {
		let { ctx, canvas } = this

		// Clear canvas
		this.clear()

		let baseScaling = canvas.width / 6
		this.dynamicScaling = baseScaling / size

		// Draw area
		this.drawSemiCircle(size)
		this.drawTriangle(size)
		this.drawRectangle(size)


		// Drawing X & Y axes with ticks on them
		this.drawCartesian(size, userInputExists)
	}

	/**
	 * This function redraws the graph.
	 *
	 * @param size area size
	 * @param userInputExists user input existence
	 */
	redrawArea(size, userInputExists) {
		if (userInputExists) {
			this.clear()
			this.drawArea(size, true)
		}
		else {
			this.clear();
			this.drawEmptyArea(size);
		}
	}

	/**
	 * This function draws a point in graph.
	 *
	 * @param x x-coord
	 * @param y y-coord
	 * @param hitStatus the area was hit or not
	 */
	drawPoint(x, y, hitStatus) {
		let { ctx, canvas } = this
		let cX = canvas.width / 2 + x * this.dynamicScaling
		let cY = canvas.height / 2 - y * this.dynamicScaling

		ctx.fillStyle = "#ca18d4"
		ctx.beginPath()
		ctx.arc(cX, cY, 3, 0, Math.PI * 2)
		ctx.fill()
	}

	/**
	 * This function applies a list of points to the graph.
	 *
	 * @param points points to be applied
	 */
	applyPoints(points) {
		for (let i = 0; i < points.length; i++) {
			this.drawPoint(points[i].x, points[i].y, points[i].hit)
		}
	}

	/**
	 * This function handles canvas clicks and performs provided logic.
	 *
	 * @param successHandler executed if succeed
	 * @param errorHandler executed if not succeed
	 */
	onClick(successHandler, errorHandler) {
		let { canvas } = this
		$(canvas).click((e) => {
			let rValue = $("input[type='checkbox'][name='r']:checked").val()
			if (!rValue || isNaN(parseInt(rValue))) {
				errorHandler("Cannot determine point coords without R being set")
				return
			}

			let x = e.clientX - canvas.getBoundingClientRect().left;
	        let y = e.clientY - canvas.getBoundingClientRect().top;
			let R = parseInt(rValue)

			let graphX = (x - canvas.width / 2) / this.dynamicScaling;
	        let graphY = (canvas.height / 2 - y) / this.dynamicScaling;

			graphX = Math.round(graphX)

			successHandler({x: graphX, y: graphY, r: R})
		})
	}
}

export { Canvas }