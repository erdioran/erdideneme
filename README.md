
## Run 
Run the xml files under the testSuite file.

RunCucumberTest class -> tags ->

regression = @REGRESSION

web = @WEB

mweb = @MWEB

admin = @ADMIN

To run a single scenario, you can @TEST the scenario tag and the run tag.


## Report 
It is created inside this file after the test is complete -> `/test-output`
It can be viewed as both html and pdf.

## Project Detail


-  The browser can be selected by changing the "browser" variable of the [app.properties](https://github.com/DikeyVitamin/qa-web/blob/master/src/test/resources/app.properties) file. Set the "mobile" parameter for mweb.

-  For test url selection, change testUrl parameters in [app.properties](https://github.com/DikeyVitamin/qa-web/blob/master/src/test/resources/app.properties) file.

-  Gets "url" and "test data" from file [data.json](https://github.com/DikeyVitamin/qa-web/blob/master/src/test/resources/data.json)

