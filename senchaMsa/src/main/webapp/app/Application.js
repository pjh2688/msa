/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('Study.Application', {
    extend: 'Ext.app.Application',

    name: 'Study',

    quickTips: false,
    platformConfig: {
        desktop: {
            quickTips: true
        }
    },
    // 4. store를 글로벌하게 사용하기 위해 등록
    stores : [
    	'ProductList'  // 5. store 폴더에 저장된 js의 이름.
    ],
    
    launch: function () {
    	// 1. 최초 접속할 때 이쪽으로 온다. 
    	Ext.Ajax.request({
    		url : '/api/sessionCheck',
    		method : 'POST',
    		success : function(response) {
    			var api = Ext.decode(response.responseText);
    			if(api['code'] == 999) {
    				// 2. code가 999면 세션이 존재하지 않다는 의미이므로 다시 로그인 페이지를 redirect
    				Ext.widget("login");
    			} else {
    				// 3. code 200이면 세션이 존재하므로 main 페이지로 redirect
    				Ext.widget("main");
    			}
    			
    		}
    	});
 //   	Ext.widget("login");
    },

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
