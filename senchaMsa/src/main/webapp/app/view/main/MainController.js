Ext.define('Study.view.main.MainController', {
    extend : 'Ext.app.ViewController',
    alias : 'controller.main',
    updatePasswordBtn : function(btn) {
//    	Ext.create("Study.view.window.UpdatePassword");
		Ext.widget("updatePassword");
    },
    logoutBtn : function(btn) {
//    	// 3. 뷰포트 삭제 -> 분기해서 처리해야할듯 viewport일때 panel일때
//		btn.up("viewport").destroy();
//		// 4. 로그인 윈도우 출력
		Ext.widget("login");
    	
    },
    menuChange : function(obj, record) {
    	var centerPage = obj.up("viewport").down("component[region=center]");
		centerPage.removeAll(true);
		centerPage.add({
			xtype : record.get("page")  // 1. store에 정의된 children의 page 속성
		})
    }
});