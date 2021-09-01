Ext.define("Study.store.MemberList", {
	extend : 'Ext.data.Store',
	alias : 'store.memberList',
	storeId : 'memberList',
	autoLoad : false,
	fields : ['member_id', 'name', 'password', 'address', 'zipcode', 'phone'],
	pageSize : 1,
	proxy : {
		type : 'ajax',
		actionMethods : {
			read : 'GET'
		},
		url : '/members',
		reader : {
			type : 'json',
			rootProperty : 'data',
			totalProperty : 'totalCount'
		}
	}
});