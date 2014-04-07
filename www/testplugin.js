var testplugin = {
    createEvent: function(title, successCallback, errorCallback) {
 	cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'TestPlugin', // mapped to our native Java class called "Calendar"
            'testPluginEntry', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "title": title
            }]
        ); 
    }
}
module.exports = testplugin;