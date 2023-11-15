<form action="${pageContext.request.contextPath}" method="POST">
	<p class="param-naming">
		X:
	</p>
	<div class="x-coord-group">
		<input id="x-1" type="button" name="x" value="-3">
		<input id="x-2" type="button" name="x" value="-2">
		<input id="x-3" type="button" name="x" value="-1">
		<input id="x-4" type="button" name="x" value="0">
		<input id="x-5" type="button" name="x" value="1">
		<input id="x-6" type="button" name="x" value="2">
		<input id="x-7" type="button" name="x" value="3">
		<input id="x-8" type="button" name="x" value="4">
		<input id="x-9" type="button" name="x" value="5">
	</div>

	<div class="y-coord-group">
		<p class="param-naming">
			Y:
		</p>
		<label for="y">
			<input id="y" name="y" type="text" placeholder="Y (-5, 3):">
		</label>
	</div>

	<div class="r-value-group">
		<p class="param-naming">
			R:
		</p>
		<label for="r-1">
			<span>1</span>
			<input id="r-1" type="checkbox" name="r" value="1">
		</label>

		<label for="r-2">
			<span>1.5</span>
			<input id="r-2" type="checkbox" name="r" value="1.5">
		</label>

		<label for="r-3">
			<span>2</span>
			<input id="r-3" type="checkbox" name="r" value="2">
		</label>

		<label for="r-4">
			<span>2.5</span>
			<input id="r-4" type="checkbox" name="r" value="2.5">
		</label>

		<label for="r-5">
			<span>3</span>
			<input id="r-5" type="checkbox" name="r" value="3">
		</label>
	</div>


	<p id="error-message"></p>
	<button id="do-check" type="button">Check</button>
</form>