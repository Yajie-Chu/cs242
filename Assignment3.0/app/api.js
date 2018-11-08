var api ={
	getData(){
		var url = `https://api.github.com/users/alsan1997`;
		return fetch(url).then((res) => res.json());
	},

	getRepo(){
		var url = `https://api.github.com/users/alsan1997/repos`;
		return fetch(url).then((res) => res.json());
	}
};

 

module.exports = api;