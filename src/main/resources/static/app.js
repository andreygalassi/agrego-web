const app = Vue.createApp({
	data() {
		return {
			count: 0
		}
	},
	methods: {
		increment() {
			this.count++
		}
	},
	mounted() {
		console.log(`The initial count is ${this.count}.`)
	}
});

const vuetify = Vuetify.createVuetify({
//	theme: {
//		defaultTheme: 'light',
//	},
})

app.use(vuetify);

app.mount('#app');

