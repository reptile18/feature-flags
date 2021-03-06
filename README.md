# feature-flags

Full stack application demo of feature flags. Uses a Feature Flag [microservice](https://github.com/kmisaal/FFService) running on a node server. Backend is Java Spring, and front end is React.

# Prerequisites

Clone the respository for the [microservice](https://github.com/kmisaal/FFService). 

Follow the instructions for starting up the service. It should be running at http://localhost:12300.

# Debug Setup

Clone this repository.

Start up the java Spring application in IntelliJ or something similar and run it in debug mode.

Open a bash console to the client React folder and run `npm install` followed by `npm start`.

# Test 

In the React application (which should open automatically to http://localhost:3000), you should see a table under a header that reads `Feature Flag Manager`.

Each row of the table represents a separate feature, each column after the first represents a different region, checkboxes represent whether the feature is turned on or off.

Verify that the initial state (sent from the microservice) is set in the table:
|                    | Asia | Korea | Europe | Japan | America
---------------------|------|-------|--------|-------|--------
useAwesomeGames      |     0|      1|       0|      1|       1
useNewFeature        |     0|      0|       0|      0|       0
Identity_Information |     0|      0|       0|      1|       1

Test clicking each of the checkboxes. Only one row is allowed to edited at a time. A save button should appear to save the flags for that row. Once the Save button is clicked, the values should be set. This proves that the post request from the front end React app is reaching through the Java Spring server and to the microservice. 

Test making a change and then clicking Cancel. The values should be reset from the server.

Test adding a new flag by typing in the input box and clicking Ok. A new row should be added with blank checkboxes. Set some values and click Save. Or click Cancel to remove.

Test expanding and collapsing the table by clicking +/- at the top right of the component.
