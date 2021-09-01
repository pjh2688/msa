Ext.define('Study.view.member.MemberListViewModel', {
    extend : 'Ext.app.ViewModel',
    alias : 'viewmodel.memberList',
    data : {
    	id : 0,
    	name : '',
    	password : 0,
    	address : '',
        searchValue : ''
    },
    stores : {
    	memberList : {
    		type : 'memberList'
    	}
    }
    
});