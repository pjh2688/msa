Ext.define('Study.view.window.UpdatePasswordController', {
    extend : 'Ext.app.ViewController',
    alias : 'controller.updatePassword',
    changeBtn : function(btn) {
    	btn.up("window").close();
    },
    closeBtn : function(btn) {
    	btn.up("window").close();
    }
});