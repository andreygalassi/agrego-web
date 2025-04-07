const _axios = axios.create({
	paramsSerializer: params => {
		const query = new URLSearchParams()
		for (const key in params) {
			const value = params[key]
			if (Array.isArray(value)) {
				value.forEach(v => query.append(key, v)) // evita sort[]=...
			} else if (value != null && typeof value !== 'undefined') {
				query.append(key, value);
			}
		}
		return query.toString()
	}
})

const _vuetify = Vuetify.createVuetify({
	defaults: {
		global: {
//			density: 'compact',
		},
	},
	theme: {
		defaultTheme: 'light',
	},
})