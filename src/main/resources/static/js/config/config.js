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
});

const vMaskV2 = VueMask.VueMaskDirective;
const vMaskV3 = {
	beforeMount: vMaskV2.bind,
	updated: vMaskV2.componentUpdated,
	unmounted: vMaskV2.unbind
};

const _vuetify = Vuetify.createVuetify({
	defaults: {
		global: {
//			density: 'compact',
			objectHelpers: {
				pick: (obj, keys) => keys.reduce((acc, key) => {
					if (obj?.hasOwnProperty(key)) acc[key] = obj[key];
					return acc;
				}, {})
			},
		},
		VDataTableServer: {
			class: 'elevation-2 data-table-borders',
			density:'compact',
		},
	},
	theme: {
		defaultTheme: 'light',
		defaults:{
		},
	},
});

const _myHelper = {
	pick: (obj, keys) => keys.reduce((acc, key) => {
		if (obj?.hasOwnProperty(key)) acc[key] = obj[key];
		return acc;
	}, {})
};