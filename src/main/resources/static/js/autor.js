const app = Vue.createApp({
	mounted() {
		console.log(`The initial count is ${this.count}.`)
	},
	data() {
		return {
			count: 0,
			config: {
				table: {
					loading: false,
					pagination: {
						itemsPerPage: 5,
						totalItems: 0,
					}
				}
			},
			headers: [
				{ title: 'ID', key: 'id',align: 'center', sortable: true, },
				{ title: 'Nome', key: 'nome', align: 'center', sortable: true, },
			],
			itens: [
				{id:1, nome:'autor1'},
				{id:2, nome:'autor2'},
			],
		}
	},
	methods: {
		increment() {
			this.count++
		}
	},
});

const vuetify = Vuetify.createVuetify({
	defaults: {
		global: {
//			density: 'compact',
		},
	},
	theme: {
		defaultTheme: 'light',
	},
})

app.use(vuetify);

app.mount('#app');

